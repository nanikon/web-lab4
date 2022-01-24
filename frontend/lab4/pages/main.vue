<template>
  <div>
    <div class="section-top section-side-space">
      <div class="block-twice transparent">
        Вы вошли как {{currentUser}}
      </div>
      <div class="block-twice transparent">
        <nuxt-link to="/" @click="logout">Выйти</nuxt-link>
      </div>
    </div>
  <div class="section-top">
    <div class="block-twice">
        <SvgArea @refresh-shots="refresh" v-bind:current-r=currentR v-bind:list=shotList ></SvgArea>
    </div>
    <div class="block-twice">
      <ShotForm @refresh-shots="refresh" @change-r="updateR"></ShotForm>
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
      shotList: [],
      currentR: 1
    }
  },

  async fetch() {
    console.log("login " + this.login)
    console.log("token " + resp.token)
    //this.$http.setToken(this.$store.getters.getToken, 'Bearer')
    await this.$http.$get('/shots').then((resp) => {
      console.log("data in fetch:")
      console.log(resp)
      this.shotList = resp
    }).catch((e) => {
      console.log(e)
      console.log(e.statusCode)
      if (e.statusCode === 401) {
        this.$store.dispatch('logout')
        this.$nuxt.context.redirect('/')
      }
      throw e;
    })
  },

  computed: {
    currentUser() {
      return this.$store.getters.getToken;
    }
  },

  methods: {
    refresh() {
      console.log("start refresh")
      this.$fetch()
    },

    updateR(r) {
      this.currentR = r
      console.log(this.currentR)
    },

    logout() {
      this.$http.setToken(null, 'Bearer')
      this.$store.dispatch('logout')
    }
  }
}
</script>

<style scoped>

</style>
