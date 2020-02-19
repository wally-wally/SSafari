<template>
  <div class="mystudygroup-mypage-list">
    <h1 class="pl-4">등록한 스터디 모임</h1>
    <MyStudyGroupList/>
    <h1 class="mt-10 pl-4">신청한 스터디 모임</h1>
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