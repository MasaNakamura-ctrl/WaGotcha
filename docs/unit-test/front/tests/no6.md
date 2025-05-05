# 単体テスト仕様書
## 実施項目
| No   | 画面 | テスト処理 | 前提条件 | 操作手順 | 期待結果 | 実施結果 |
| --- | ----------- | ------- | ------- | ------- | ------- | ------- |
| 6 | 一覧画面 | 削除処理 | tsutsumeki.jsにインポートできるデータが存在する | 「消つ」ボタンを押下 | 押下した分の投稿が画面から表示されなくなること |OK|
## 表示されている画面
![トップ画面](./img/wagotcha-top.png)
## tsutsumeki.js
```javascript
import { ref } from 'vue'

export const tsutsumekis = ref([
    {id:1, tsutsumeki: "六段の調べやりたい人いない？"},
    {id:2, tsutsumeki: "来週演奏会やります！"},
    {id:3, tsutsumeki: "○市でもくもく会やってませんか？"}
])
```
## テスト実施
### "六段の調べやりたい人いない？"の「消つ」ボタンを押下
![消つボタン押下語](./img/ketsu-clicked.png)