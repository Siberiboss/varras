<template>
  <div>
    <h3>Items</h3>

    <button @click="toggleForm">Add Item</button>

    <ItemForm
      v-if="showForm"
      :attributes="attributes"
      @created="onCreated"
    />

    <ul>
      <li v-for="i in items" :key="i.id">
        {{ i.name }} ({{ i.attribute?.name }})
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
      attributes: [],
      showForm: false
    }
  },
  async mounted() {
    this.fetch()
  },
  methods: {
    async fetch() {
      const res1 = await axios.get('/api/items')
      const res2 = await axios.get('/api/attributes')
      this.items = res1.data
      this.attributes = res2.data
    },
    toggleForm() { this.showForm = !this.showForm },
    onCreated() {
      this.showForm = false
      this.fetch()
    }
  }
}
</script>
