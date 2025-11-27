<template>
  <div>
    <h1>Varras</h1>

    <Login v-if="!token" @logged="onLogged" />

    <div v-else>
      <div :token="token">Tere, {{token.username}}</div>
      <button @click="logout">Logout</button>

      <Categories />
      <Fields />
      <Items />
    </div>
  </div>
</template>

<script>
import { ref } from 'vue'
import Login from './components/Login.vue'
import Categories from './components/Categories.vue'
import Items from './components/Items.vue'
import Fields from "./components/Fields.vue";

export default {
  components: { Fields, Login, Categories, Items },
  setup() {
    const token = ref(localStorage.getItem('token'))

    const onLogged = (resp) => {

      const t = resp.token;
      token.value = JSON.parse(atob(t));
      localStorage.setItem('token', t)
    }

    const logout = () => {
      token.value = null
      localStorage.removeItem('token')
    }

    return { token, onLogged, logout }
  }
}
</script>
