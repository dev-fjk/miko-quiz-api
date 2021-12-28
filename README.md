# miko-quiz-api
みこちのクイズアプリ用Rest Api

## entry point
[Swagger UI](http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/)

## パッケージ構成
| dir1 | dir2  | dir3           | dir4       | dir5     | description
|----  |----   |----            |----        |----      | ----
| doc  |       |                |            |          | 設計書配置用ディレクトリ
| src  |       |                |            |          |
|      | main  |                |            |          |
|      |       | common         |            |          | 共通定義用パッケージ
|      |       |                | config     |          | 共通的なDIのConfig定義
|      |       |                | constant   |          | 定数定義用パッケージ
|      |       | application    |            |          | アプリケーション層　業務ロジック関連
|      |       |                | exception  |          | 自作業務例外定義用パッケージ
|      |       |                | service    |          | Serviceクラスの実装 メインのビジネスロジック実装箇所
|      |       | domain         |            |          | ドメイン層 アプリ内部で使用するmodelとIFの定義
|      |       |                | model      |          |
|      |       |                |            | dto      | アプリケーション内の中間加工用のmodel定義
|      |       |                | service    |          | サービスIFの定義
|      |       |                | repository |          | リポリトリIFの定義
|      |       | infrastructure |            |          | インフラ層 外部サービスへのアクセス部
|      |       |                | repository |          | リポジトリ実装クラスの作成 daoとサービス層間のビジネスロジック盛り込み
|      |       |                | dao        |          | domaのデータアクセスリポジトリ DBにSQLを発行
|      |       |                | model      |          |
|      |       |                |            | entity   | DBから取得したデータ保持用のmodel定義
|      |       | presentation   |            |          | プレゼンテーション層 Restの送受信周りのソース定義
|      |       |                | controller |          | RestControllerの定義用パッケージ
|      |       |                | validation |          | 入力チェック用処理の定義
|      |       |                | converter  |          | IF周りの型変換を行うパッケージ
|      |       |                | model      |          |
|      |       |                |            | form     | Requestのbodyからバインドされるformのmodelクラス定義
|      |       |                |            | response | Apiのレスポンスとして返却するmodelの定義

## 参考
- [Doma リファレンス](http://doma.seasar.org/reference/index.html)
- [Doma Framework](https://github.com/domaframework/doma-spring-boot)
- [ControllerからSwaggerを自動生成](https://qiita.com/rhirabay/items/f7527c91b5defc424b9c)
