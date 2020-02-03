<template>
     <v-footer
    dark
    padless
  >
    <v-card
      class="flex"
      flat
      tile
      style="color: #f7b157;"
    >
      <v-card-text class="py-2 white--text text-center">
        2020-01-30 Completed, <strong>Daejeon Team A5, <span @click="goGitlab()" class="A5-Gitlab">Gitlab</span></strong>
      </v-card-text>
    </v-card>
  </v-footer>

</template>

<script>
export default {
  name: 'Footer',
  components: {

  },
  methods: {
    goGitlab() {
      window.open('https://lab.ssafy.com/webmobile1-sub2/s02p12b101')
    }
  }
}
</script>

<style>
  .A5-Gitlab {
    color: #f7b157;
  }

  .A5-Gitlab:hover {
    cursor: pointer;
  }
</style>