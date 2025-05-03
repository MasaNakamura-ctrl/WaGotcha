<script setup>
import { ref } from 'vue'
import { tsutsumekis } from './tsutsumeki.js'

// データ追加
const inputText = ref('')
// 仮データの保持レコード数に1を加えたもの
let id_count = 4;
// IDへの対応はRESTAPIと連携次第そちらへ移行
function addTsutsumeki(){
    console.log(id_count)
    if (inputText.value.trim()) {
        tsutsumekis.value.push({id:id_count, tsutsumeki: inputText.value })
        inputText.value = ''
        id_count += 1;
    }
    console.log(id_count)
}

// データ更新
let editingIndex = ref(null)
const editingText = ref('')
function editTsutsumeki(index){
    editingIndex.value = index
    editingText.value = tsutsumekis.value[index].tsutsumeki
}
function completeEdittingTsutsumeki(index){
    editingIndex.value = null
}
function quitEdittingTsutsumeki(index){
    tsutsumekis.value[index].tsutsumeki = editingText.value
    editingIndex.value = null
}

// データ削除
function deleteTsutsumeki(index){
    tsutsumekis.value.splice(index,1)
}

// WaGotcha押下(RESTAPIと連携次第別対応)
// WaGotchaされたインデックスを記録(RESTAPIと連携次第別対応)
const clickedIndices = ref(new Set())
function clickWaGotcha(index){
    clickedIndices.value.add(tsutsumekis.value[index]["id"])
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
        <div v-if="editingIndex !== index">
            <p class="Tsutsumeki-words">{{ tsutsumeki.tsutsumeki }}</p>
            <button class="WaGotcha" @click="clickWaGotcha(index)"
            :class= "{active: clickedIndices.has(tsutsumeki.id)}"
            >WaGotcha</button>
            <button @click="editTsutsumeki(index)">改む</button>
            <button @click="deleteTsutsumeki(index)">消つ</button>
        </div>
        <div v-else>
            <textarea v-model="tsutsumekis[index].tsutsumeki" class="Tsutsumeki-text" maxlength="140"></textarea>
            <button class="WaGotcha" @click="quitEdittingTsutsumeki(index)">WaGotcha</button>
            <button @click="completeEdittingTsutsumeki(index)">記す</button>
            <button @click="quitEdittingTsutsumeki(index)">辞む</button>
        </div>
    </div>
</template>