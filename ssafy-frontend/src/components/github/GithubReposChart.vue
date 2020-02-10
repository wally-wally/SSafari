<script>
import { Bar } from 'vue-chartjs'
export default {
  name: 'GithubReposChart',
  extends: Bar,
  props: {
    datacollection: { type: Object }
  },
  data() {
    return {
      datacollection: this.datacollection,
      options: {}
    }
  },
  mounted() {
    this.renderChart(this.datacollection, this.options)
  }
}
</script>

<style>

</style>