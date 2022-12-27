# java-seguro

Java Seguro - aprenda Java e conceitos de segurança

# YouTube

## Tudo junto

* [Playlist](https://youtube.com/playlist?list=PL2XKkc9gm2WTPFgDGa5pVrIooACUu6DTI)

## Por partes

* [Java Seguro - parte 1 - MD5](https://youtu.be/fwpWAH9-b_g)
* [Java Seguro - parte 2 - MD5 com salt](https://youtu.be/EAv_hsmGEW0)
* [Java Seguro - parte 3 - base64](https://youtu.be/EqtxxoI9qfE)
* [Java Seguro - parte 4 - DES](https://youtu.be/k2eHBNlmB6U) 
* [Java Seguro - parte 5 - AES e RSA](https://youtu.be/EBGopqWf9OE) 

## Teste de ECB com imagens

```
# Abra a imagem e converta para formato PPM (Portable Pixmap Format)
gimp dev-multitask.png
# extrair o header
head -n 4 dev-multitask.ppm > header.txt
# extrair o resto
tail -n +5 dev-multitask.ppm > body.bin
# encrypt com ECB (Electronic CodeBook)
openssl enc -aes-128-ecb -nosalt -pbkdf2 -pass pass:"Boaglio" -in body.bin -out body.ecb.bin

# encrypt com ECB + salt
openssl enc -aes-128-ecb -salt -pbkdf2 -pass pass:"Boaglio" -in body.bin -out body.ecb-salt.bin

# encrypt com CBC (cipher block chaining) - semantically secure
openssl enc -aes-128-cbc -pbkdf2 -pass pass:"Boaglio" -in body.bin -out body.cbc.bin

# montar a imagem resultado
cat header.txt body.ecb.bin > dev-multitask.ecb.ppm
cat header.txt body.ecb-salt.bin > dev-multitask.ecb-salt.ppm
cat header.txt body.cbc.bin > dev-multitask.cbc.ppm
```

## Referências

* [Cryptography 101 for Java developers by Michel Schudel](https://youtu.be/1925zmDP_BY)
* [ECB Image Test](https://words.filippo.io/the-ecb-penguin/)
* [Java AES encryption and decryption](https://mkyong.com/java/java-aes-encryption-and-decryption/)
