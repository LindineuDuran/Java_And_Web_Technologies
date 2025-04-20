# Build
mvn clean package && docker build -t br.com.lduran/hello-cdi .

# RUN

docker rm -f hello-cdi || true && docker run -d -p 8080:8080 -p 4848:4848 --name hello-cdi br.com.lduran/hello-cdi 