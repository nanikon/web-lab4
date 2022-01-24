<template>
  <div class="section-middle">
    <div class="block-alone">
      <h2>{{ statusCode }}</h2>
      <p>{{ message }}</p>
      <nuxt-link to="/">На главную страницу</nuxt-link>
    </div>
  </div>
</template>

<script>
import Vue from "vue";

export default Vue.extend({
  name: "ErrorPage",

  props: {
    error: {
      type: Object,
      default: null,
      required: true,
    }
  },

  computed: {
    statusCode () {
      return (this.error && this.error.statusCode) || 500
    },
    message () {
      return this.error.response.data.errorMessage || this.error.message || 'Ошибка сервера без сообщения!'
    }
  },
})
</script>

<style scoped>

</style>
