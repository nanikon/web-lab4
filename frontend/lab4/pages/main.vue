<template>
  <div>
  <div class="section-top">
    <div class="block-twice">
        fdf
    </div>
    <div class="block-twice">
      <ShotForm @refresh-shots="refresh"></ShotForm>
    </div>
  </div>
    <h2>Результаты</h2>
    <ShotsTable v-bind:list=shotList v-if=shotList.length></ShotsTable>
    <p class="message" v-else>Ни одного выстрела пока не сделано</p>
  </div>
</template>

<script>
import ShotForm from "~/components/forms/ShotForm";
export default {
  name: "main",
  components: {ShotForm},
  data() {
    return {
      shotList: []
    }
  },

  async fetch() {
    await this.$http.$get('/shots').then((resp) => {
      console.log("data in fetch:")
      console.log(resp)
      this.shotList = resp
    }).catch((e) => {
      console.log(e)
      console.log(e.statusCode)
      if (e.statusCode === 401) {
        console.log(e.response.data.errorMessage)
        this.$nuxt.context.redirect('/')
      }
    })
  },

  methods: {
    refresh() {
      console.log("start refresh")
      this.$fetch()
    }
  }
}
</script>

<style scoped>

</style>
