<template>
  <form method="post" @submit.prevent="register">
    <p>
      <label for="login">Введите имя пользователя:</label><br/>
      <input type="text" name="login" id="login" required v-model.trim="login"/>
    </p>
    <p>
      <label for="password">Введите пароль:</label><br/>
      <input type="password" name="password" id="password" required v-model="password"/>
    </p>
    <p>
      <label for="repeat_password">Повторите пароль:</label><br/>
      <input type="password" name="repeat_password" id="repeat_password" required v-model="repeat_password"/>
    </p>
    <p>
      <button>Зарегистрироваться</button>
      <nuxt-link to="/">Авторизация</nuxt-link>
    </p>
    <p v-if="errors" class="error">
      {{ errors }}
    </p>
  </form>
</template>

<script>
export default {
  name: "RegForm",

  data() {
    return {
      login: '',
      password: '',
      repeat_password: '',
      errors: null
    }
  },

  methods: {
    async register() {
      if (this.password !== this.repeat_password) {
        this.errors = 'Пароли не совпадают'
        return
      }
      await this.$http.$post('/users/register',
        {
          login: this.login,
          password: this.password
        }).then((resp) => {
        this.$router.replace('/?login=' + resp.login)
      }).catch((e) => {
        console.log(e.stausCode)
        console.log(this.errors = e.response.data.errorMessage)
        if (e.statusCode === 404 || e.statusCode === 400) {
          this.errors = e.response.data.errorMessage
        }
        else { throw e; }
      })
    }
  }
}
</script>

