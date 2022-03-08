<template>
  <table>
    <thead>
    <tr>
      <th>X</th>
      <th>Y</th>
      <th>R</th>
      <th>Попали?</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="shot in list" :key="shot.id">
      <td>{{ shot.x }}</td>
      <td>{{ shot.y }}</td>
      <td>{{ shot.r }}</td>
      <td>{{ shot.hit ? "Да" : "Нет" }}</td>
    </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: "ShotsTable",

  props: [ 'list' ]
}
</script>
