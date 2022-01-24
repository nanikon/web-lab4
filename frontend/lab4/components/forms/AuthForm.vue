<template>
  <form method="post" @submit.prevent="auth">
    <p>
      <label for="login">Введите имя пользователя:</label><br/>
      <input type="text" name="login" id="login" required v-model.trim="login"/>
    </p>
    <p>
      <label for="password">Введите пароль:</label><br/>
      <input type="password" name="login" id="password" required v-model="password"/>
    </p>
    <p>
      <button>Войти</button>
      <nuxt-link to="/register">Регистрация</nuxt-link>
    </p>
    <p v-if="errors" class="error">
      {{ errors }}
    </p>
  </form>
</template>

<script>
export default {
  name: "AuthForm",

  data() {
    return {
      login: '',
      password: '',
      errors: null
    }
  },

  methods: {
    async auth() {
      await this.$http.$post('/users/login',
        {
          login: this.login,
          password: this.password
        }).then((resp) => {
          this.$http.setToken(resp.token, 'Bearer')
          this.$router.replace('/main')
        }
      ).catch((e) => {
        console.log(e.statusCode)
        console.log(e.response.data.errorMessage)
        if (e.statusCode === 401 || e.statusCode === 404) {
          this.errors = e.response.data.errorMessage
        }
        else { throw e; }
      })
    }
  }
}
</script>

