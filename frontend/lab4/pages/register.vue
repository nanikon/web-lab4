<template>
  <div class="section-middle">
    <div class="block-alone">
      <h2>Регистрация</h2>
      <RegForm></RegForm>
    </div>
  </div>
</template>

<script>
import RegForm from "~/components/forms/RegForm";
export default {
  name: "register",
  components: {RegForm}
}
</script>

