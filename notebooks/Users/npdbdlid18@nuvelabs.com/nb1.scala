// Databricks notebook source

val AccessKey = dbutils.secrets.get(scope = "aws", key = "aws-access-key")
// Encode the Secret Key as that can contain "/"
val SecretKey = dbutils.secrets.get(scope = "aws", key = "aws-secret-key")
val EncodedSecretKey = SecretKey.replace("/", "%2F")
val AwsBucketName = "<aws-bucket-name>"
val MountName = "<mount-name>"

dbutils.fs.mount(s"s3a://$AccessKey:$EncodedSecretKey@$AwsBucketName", s"/mnt/$MountName")
display(dbutils.fs.ls(s"/mnt/$MountName"))

// COMMAND ----------

val df=spark.read.json("")

// COMMAND ----------

df.write.parquet("/mnt/ss1/pawan/output/out1")