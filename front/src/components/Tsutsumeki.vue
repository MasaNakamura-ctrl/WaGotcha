<script setup>
import { ref } from 'vue'
import { tsutsumekis } from './tsutsumeki.js'

const inputText = ref('')
const clickedIndices = ref(new Set()) // WaGotchaされたインデックスを記録

// データ追加
// IDへの対応はRESTAPIと連携次第そちらへ移行
function addTsutsumeki(){
    const tailId = tsutsumekis.value.length + 1
    if (inputText.value.trim()) {
        tsutsumekis.value.push({id:tailId, tsutsumeki: inputText.value })
        inputText.value = ''
    }
}
// データ削除
function deleteTsutsumeki(index){
    tsutsumekis.value.splice(index,1)
}

function clickWaGotcha(index){
    clickedIndices.value.add(index)
}
</script>
<template>
    <h1>ツツメキ</h1>
    <div class="Tsutsumeki-create">
        <textarea v-model="inputText" class="Tsutsumeki-text" maxlength="140" placeholder="ツツメキしてWaGotchaしてもらおう！">
        </textarea>
        <button @click="addTsutsumeki">ツツメキ</button>
    </div>
    <div class="Tsutsumeki-posted" v-for="(tsutsumeki, index) in tsutsumekis">
        <p class="Tsutsumeki-words">{{ tsutsumeki.tsutsumeki }}</p>
        <button class="WaGotcha" @click="clickWaGotcha(index)"
        :class= "{active: clickedIndices.has(index)}"
        >WaGotcha</button>
        <button>改む</button>
        <button @click="deleteTsutsumeki(index)">消つ</button>
    </div>
</template>