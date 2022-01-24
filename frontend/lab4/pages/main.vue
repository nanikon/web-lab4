<template>
  <div>
  <div class="section-top">
    <div class="block-twice">
        fdf
    </div>
    <div class="block-twice">fdf</div>
  </div>
    <h2>Результаты</h2>
    <ShotsTable v-bind:list=shotList></ShotsTable>
  </div>
</template>

<script>
export default {
  name: "main",

  data() {
    return {
      shotList: []
    }
  },

  async fetch() {
    await this.$http.$get('/shots').then((resp) => {
      console.log("data:")
      console.log(resp)
      this.shotList = resp
    }).catch((e) => {
      console.log(e)
      console.log(e.statusCode)
      if (e.statusCode === 401) {
        alert(e.response.data.errorMessage)
        this.$nuxt.context.redirect('/')
      }
    })
  },
}
</script>

<style scoped>

</style>
