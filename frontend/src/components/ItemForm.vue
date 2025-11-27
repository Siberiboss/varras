<template>
  <div style="border:1px solid #ccc; padding:10px; margin-top:10px">
    <h4>Create item</h4>

    <input v-model="name" placeholder="title" />
    <input v-model="condition" placeholder="condition" />

    <h4>Dynamic fields</h4>

<!--    <div v-for="(f, index) in dynamicFields" :key="index">-->
<!--      <input v-model="f.key" placeholder="key" />-->
<!--      <input v-model="f.value" placeholder="value" />-->
<!--      <button @click="removeField(index)">x</button>-->
<!--    </div>-->

    <button @click="addField">Add field</button>

    <br /><br />
    <button @click="save()">Save</button>
  </div>
</template>

<script>
import axios from 'axios'
import { emitter } from '../event-bus.js'

export default {
  props: ['attributes', 'activeCategory'],
  data() {
    return {
      name: '',
      condition: '',
      activeCategory: null
    }
  },
  created() {
    emitter.on("categoryActivated", (number) => {
      console.log(number)
      this.activeCategory = number;
    });
  },
  methods: {
    async save() {
      if (!this.activeCategory) {
        alert("Please select a category!")
        return;
      }

      await axios.post(`/api/categories/${this.activeCategory}/items`, {
        name: this.name,
        condition: this.condition
      })
      this.$emit('created')
    }
  }
}
</script>
