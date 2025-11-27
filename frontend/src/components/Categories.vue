<template>
  <div>
    <h3>Categories</h3>

    <div>Mida soovid arvele võtta?</div>
    <input v-model="newName" placeholder="Potitaimed" />
    <button @click="create">Lisa</button>

    <ul>
      <li
          v-for="c in categories"
          :key="c.id"
          :class="{ active: activeCategory === c.id }"
          @click="activeCategory = c.id;console.log(activeCategory)"
          style="cursor:pointer;"
      >{{ c.name }}
        <button @click="remove(c.id)">x</button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      categories: [],
      newName: '',
    }
  },
  async mounted() {
    this.fetch()
  },
  methods: {
    async fetch() {
      const res = await axios.get('/api/categories')
      this.categories = res.data
    },
    async create() {
      await axios.post('/api/categories', { name: this.newName },
          {
            headers: { Authorization: `Bearer ${this.token}` }
          })
      this.newName = ''
      this.fetch()
    },
    async remove(id) {
      await axios.delete(`/api/categories/${id}`)
      this.fetch()
    }
  }
}
</script>

<style scoped>
.active {
  font-weight: bold;
  color: black;
  background: limegreen;
}

</style>
