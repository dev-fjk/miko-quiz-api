# miko-quiz-api
みこちのクイズアプリ用Rest Api

## entry point
TODO コントローラ完成後Swaggerで自動生成してdoc配下に配置

## DB
ER図だけ作成済み

## パッケージ構成
| dir1 | dir2  | dir3           | dir4       | dir5     | description
|----  |----   |----            |----        |----      | ----
| doc  |       |                |            |          | 設計書配置用ディレクトリ
| src  |       |                |            |          |
|      | main  |                |            |          |
|      |       | application    |            |          | アプリケーション層　業務ロジック関連
|      |       |                | config     |          | DI定義用クラスの作成
|      |       |                | exception  |          | 自作業務例外定義用パッケージ
|      |       |                | service    |          | Serviceクラスの実装 メインのビジネスロジック実装箇所
|      |       | domain         |            |          | ドメイン層 アプリ内部で使用するmodelとIFの定義
|      |       |                | model      |          | 
|      |       |                |            | entity   | DBから取得したデータ保持用のmodel定義
|      |       |                |            | dto      | アプリケーション内の中間加工用のmodel定義
|      |       |                | service    |          | サービスIFの定義
|      |       |                | repository |          | リポリトリIFの定義
|      |       | infrastructure |            |          | インフラ層 外部サービスへのアクセス部
|      |       |                | dao        |          | domaのデータアクセスリポジトリ DBにSQLを発行
|      |       |                | repository |          | リポジトリ実装クラスの作成 daoとサービス層間のビジネスロジック盛り込み
|      |       | presentation   |            |          | プレゼンテーション層 Restの送受信周りのソース定義
|      |       |                | controller |          | RestControllerの定義用パッケージ
|      |       |                | model      |          |
|      |       |                |            | form     | Requestのbodyからバインドされるformのmodelクラス定義
|      |       |                |            | response | Apiのレスポンスとして返却するmodelの定義

## 参考
- [Doma リファレンス](http://doma.seasar.org/reference/index.html)
- [Doma Framework](https://github.com/domaframework/doma-spring-boot)
- [ControllerからSwaggerを自動生成](https://qiita.com/rhirabay/items/f7527c91b5defc424b9c)
