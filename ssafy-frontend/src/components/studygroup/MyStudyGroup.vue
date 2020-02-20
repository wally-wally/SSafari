<template>
  <div class="mystudygroup-mypage-list">
    <div class="mt-2 mb-5 text-center" style="font-size: 30px; font-weight: bold;">등록한 스터디 모임</div>
    <MyStudyGroupList/>
    <div class="mt-2 mb-5 text-center" style="font-size: 30px; font-weight: bold;">신청한 스터디 모임</div>
    <MyStudyParticipated/>
  </div>
</template>

<script>
import MyStudyGroupList from '@/components/studygroup/MyStudyGroupList'
import MyStudyParticipated from '@/components/studygroup/MyStudyParticipated'
export default {
  name: 'MyStudyGroup',
  components: {
    MyStudyGroupList,
    MyStudyParticipated
  }
}
</script>

<style>
  .mystudygroup-mypage-list > h1 {
    font-family: 'Noto Sans KR';
    font-weight: bold;
  }
</style>