# API単体テスト
## 初回デプロイ前
APIの単体テストは[JUnitやMockitoを用いたテストコード](../../../api/src/test/java/com/example/api/tsutsumeki/)にて実施

[![api](https://github.com/MasaNakamura-ctrl/WaGotcha/actions/workflows/api.yml/badge.svg)](https://github.com/MasaNakamura-ctrl/WaGotcha/actions/workflows/api.yml)

## 開発ブランチでのテストについて
現在、`develop`ブランチでログイン機能（Spring Security）を実装中です。

その影響で、一部のAPIに認証が必要となるため、既存の単体テスト（MockMvc等）が未対応の状態です。

本リポジトリの`main`ブランチではまだログイン機能はマージしておらず、テストコードもSecurity非対応の状態を維持しています。

ログイン機能の実装完了後に、順次テストコードの修正を予定しています。

テスト失敗の一因が認証周りであることは認識しており、段階的に対応を進めています。