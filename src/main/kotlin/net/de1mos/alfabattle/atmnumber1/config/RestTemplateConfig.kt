package net.de1mos.alfabattle.atmnumber1.config

import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.io.File
import java.io.InputStream
import javax.net.ssl.SSLContext


@Configuration
class RestTemplateConfig {

    @Value("\${key.store}")
    lateinit var keyStore: Resource

    @Value("\${key.store.password}")
    lateinit var keyStorePassword: String

    @Bean
    fun restTemplate(): RestTemplate? {
        val jks = File.createTempFile("server_keystore", ".jks")
        keyStore.inputStream.use {
            FileUtils.copyInputStreamToFile(it, jks)
        }

        val sslContext: SSLContext = SSLContextBuilder()
                .loadKeyMaterial(jks, keyStorePassword.toCharArray(), keyStorePassword.toCharArray())
                .build()
        val socketFactory = SSLConnectionSocketFactory(sslContext)
        val httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build()
        val factory = HttpComponentsClientHttpRequestFactory(httpClient)
        return RestTemplate(factory)
    }
}