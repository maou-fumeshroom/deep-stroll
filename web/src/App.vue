<template lang="pug">

</template>

<script>

export default {
  provide () {
    return {
      reload: this.reload
    }
  },
  data () {
    return {
      isRouterAlive: true,
    }
  },
  watch: {
    $route: {
      handler (val, oldVal) {
        this.$root.managePage = !((val.path).indexOf('managepage') === -1)
        this.$root.manage = !((val.path).indexOf('manage') === -1)
      },
      immediate: true
    }

  },
  created () {

  },
  methods: {
    reload () {
      this.isRouterAlive = false
      this.$nextTick(function () {
        this.isRouterAlive = true
      })
    },
  }
}
</script>

<style scoped>

</style>
