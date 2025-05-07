# API DB結合テスト
## 結合テスト項目一覧表
| No   | メソッド | テスト処理 | 前提条件 | 操作手順 | 期待結果 | 実施結果 |
| --- | ----------- | ------- | ------- | ------- | ------- | ------- |
| 1 | GET | 正常ケース | DBにインポートできるデータが存在する | APIとDBを立ち上げPostmanにてGETリクエストとしてhttp://localhost:8080/api/tsutsumekisを送る | DBに存在するデータがjson形式で返されること |[OK](./tests/no1.md)|
| 2 | GET | 正常ケース | DBにインポートできるデータが存在しない | APIとDBを立ち上げPostmanにてGETリクエストとしてhttp://localhost:8080/api/tsutsumekisを送る | 空のデータがjson形式で返されること |[OK](./tests/no2.md)|
| 3 | GET | 異常ケース | DBにインポートできるデータが存在する | APIとDBを立ち上げた後DBを停止しPostmanにてGETリクエストとしてhttp://localhost:8080/api/tsutsumekisを送る | エラーとしてInternal server errorが返されること |[OK](./tests/no3.md)|
| 4 | POST | 正常ケース | APIとDBを立ち上げている | PostmanにてPOSTリクエストとしてhttp://localhost:8080/api/tsutsumekisに適当なテキストを送る | 送ったテキストが追加された新たなデータとしてjson形式で返されること |[OK](./tests/no4.md)|
| 5 | PUT | 正常ケース | APIとDBを立ち上げており、データ内に該当するIDが存在する | PostmanにてPUTリクエストとしてhttp://localhost:8080/api/tsutsumekis/{id}に適当なテキストを送る | 送ったテキストが更新された新たなデータとしてjson形式で返されること |[OK](./tests/no5.md)|
| 6 | PUT | 異常ケース | APIとDBを立ち上げており、データ内に該当するIDが存在しない | PostmanにてPUTリクエストとしてhttp://localhost:8080/api/tsutsumekis/{id}に適当なテキストを送る | データの更新が行われず"Not Found"がjson形式で返されること |[OK](./tests/no6.md)|
| 7 | DELETE | 正常ケース | APIとDBを立ち上げており、データ内に該当するIDが存在する | PostmanにてDELETEリクエストとしてhttp://localhost:8080/api/tsutsumekis/{id}に適当なテキストを送る | 送ったテキストに該当するデータが削除されjson形式で返されること |[OK](./tests/no7.md)|
| 8 | DELETE | 異常ケース | APIとDBを立ち上げており、データ内に該当するIDが存在しない | PostmanにてDELETEリクエストとしてhttp://localhost:8080/api/tsutsumekis/{id}に適当なテキストを送る | データの削除が行われず"Not Found"がjson形式で返されること |[OK](./tests/no8.md)|
