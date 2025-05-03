<script setup>
import { ref } from 'vue'
import { tsutsumekis } from './tsutsumeki.js'

const inputText = ref('')

// データ追加
function addTsutsumeki(){
    if (inputText.value.trim()) {
        tsutsumekis.value.push({ tsutsumeki: inputText.value })
        inputText.value = ''
    }
}
// データ削除
function deleteTsutsumeki(index){
    tsutsumekis.value.splice(index,1)
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
        <button>WaGotcha</button>
        <button>改む</button>
        <button @click="deleteTsutsumeki(index)">消つ</button>
    </div>
</template>