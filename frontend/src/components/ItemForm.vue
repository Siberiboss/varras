<template>
  <div style="border:1px solid #ccc; padding:10px; margin-top:10px">
    <h4>Create item</h4>

    <input v-model="title" placeholder="title" />
    <input v-model="location" placeholder="location" />

    <select v-model="categoryId">
      <option v-for="c in categories" :key="c.id" :value="c.id">
        {{ c.name }}
      </option>
    </select>

    <h4>Dynamic fields</h4>

    <div v-for="(f, index) in dynamicFields" :key="index">
      <input v-model="f.key" placeholder="key" />
      <input v-model="f.value" placeholder="value" />
      <button @click="removeField(index)">x</button>
    </div>

    <button @click="addField">Add field</button>

    <br /><br />
    <button @click="save(categoryId)">Save</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  props: ['categories'],
  data() {
    return {
      title: '',
      location: '',
      categoryId: null,
      activeCategory: null,
      dynamicFields: []
    }
  },
  methods: {
    addField() {
      this.dynamicFields.push({ key: '', value: '' })
    },
    removeField(i) {
      this.dynamicFields.splice(i, 1)
    },
    async save(categoryId) {
      console.log(this.activeCategory);
      if (!this.activeCategory) {
        alert("Please select a category!")
        return
      }

      const dyn = {}
      for (const f of this.dynamicFields) {
        dyn[f.key] = f.value
      }
      categoryId = this.activeCategory;
      await axios.post(`/api/categories/${this.activeCategory}/items`, {
        title: this.title,
        location: this.location,
        dynamicFields: dyn
      })
      this.$emit('created')
    }
  }
}
</script>
