<template>
  <div>
    <h3>Items</h3>

    <button @click="toggleForm">Add Item</button>

    <ItemForm
      v-if="showForm"
      :categories="categories"
      @created="onCreated"
    />

    <ul>
      <li v-for="i in items" :key="i.id">
        {{ i.title }} ({{ i.category?.name }})
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import ItemForm from './ItemForm.vue'

export default {
  components: { ItemForm },
  data() {
    return {
      items: [],
      categories: [],
      showForm: false
    }
  },
  async mounted() {
    this.fetch()
  },
  methods: {
    async fetch() {
      const res1 = await axios.get('/api/items')
      const res2 = await axios.get('/api/categories')
      this.items = res1.data
      this.categories = res2.data
    },
    toggleForm() { this.showForm = !this.showForm },
    onCreated() {
      this.showForm = false
      this.fetch()
    }
  }
}
</script>
