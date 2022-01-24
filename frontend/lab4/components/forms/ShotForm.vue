<template>
  <form method="post" @submit.prevent="sendShot" @click="cleanErrors">
    <p>
      <label for="input-x">Выберите коордианту х:</label> <br>
      <select v-model.number="x" id="input-x" required>
        <option>-4</option>
        <option>-3</option>
        <option>-2</option>
        <option>-1</option>
        <option selected>0</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
      </select>
    </p>
    <p>
      <label for="input-y">Введите координату у:</label> <br/>
      <input type="text" v-model.number="y" id="input-y" placeholder="вещественное число от -5 до 5" required/>
    </p>
    <p>
      <label for="input-r">Выберите радиус r:</label> <br>
      <select v-model.number="r" id="input-r" required @change="changeR">
        <option disabled>-4</option>
        <option disabled>-3</option>
        <option disabled>-2</option>
        <option disabled>-1</option>
        <option disabled>0</option>
        <option selected>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
      </select>
    </p>
    <p>
      <button>Выстрелить!</button>
    </p>
    <p v-if="errors" class="error">
      {{ errors }}
    </p>
  </form>
</template>

<script>
const Y_MIN = -5
const Y_MAX = 5

function isNumeric(n) {
  return !isNaN(parseFloat(n)) && isFinite(n);
}

export default {
  name: "ShotForm",

  data() {
    return {
      x: 0,
      y: 0,
      r: 1,
      errors: null
    }
  },

  emits: ['refresh-shots', 'change-r'],

  methods: {
    async sendShot() {
      if (!this.validateY()) {
        this.errors = "Координата y должна быть числом от -5 до 5"
        return
      }
      await this.$http.$post('/shots',
        {
          x: this.x,
          y: this.y,
          r: this.r
        }).then((res) => {
          console.log("Saved shot:")
          console.log(res)
          this.$emit('refresh-shots')
      }).catch((e) => {
        console.log(e)
        console.log(e.statusCode)
        if (e.statusCode === 401) {
          alert(e.response.data.errorMessage)
          this.$router.replace('/')
        } else if (e.statusCode === 400) {
          this.errors = e.response.data.errorMessage
        } else { throw e; }
      })
    },

    validateY() {
      return (isNumeric(this.y) && (this.y >= Y_MIN) && (this.y <= Y_MAX))
    },

    cleanErrors() {
      this.errors = null
    },

    changeR() {
      this.$emit('change-r', this.r)
    }
  }
}
</script>
