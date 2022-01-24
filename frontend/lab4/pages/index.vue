<template>
  <div class="section-middle">
    <div class="block-alone">
      <h2>Авторизация</h2>
      <AuthForm></AuthForm>
    </div>
  </div>
</template>

<script>
import AuthForm from "~/components/forms/AuthForm";
export default {
  name: 'IndexPage',
  components: {AuthForm}
}
</script>
