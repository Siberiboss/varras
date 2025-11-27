<template>
  <div>
    <h2>Login (mock)</h2>
    <input v-model="username" placeholder="username" />
    <input v-model="password" type="password" placeholder="password" />
    <button @click="login">Login</button>
    <p v-if="err">{{ err }}</p>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  emits: ['logged'],
  data() {
    return { username: '', password: '', err: null }
  },
  methods: {
    async login() {
      try {
        const res = await axios.post('/api/auth/login', {
          username: this.username,
          password: this.password
        })
        this.$emit('logged', res.data)
      } catch (e) {
        console.log(e);
        this.err = 'Login failed'
      }
    }
  }
}
</script>
