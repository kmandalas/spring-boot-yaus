job "demo-webapp" {
  datacenters = ["europe-west1-c", "europe-west1-d"]

  group "demo" {
    count = 1

    network {
      port "http" {
        to = 9090
      }
    }

    service {
      name = "demo-webapp"
      port = "http"
    }

    task "server" {
      env {
        PORT    = "${NOMAD_PORT_http}"
        NODE_IP = "${NOMAD_IP_http}"
      }

      resources {
        cpu    = 200
        memory = 128
      }

      template {
        data = <<EOH
          SPRING_DATA_REDIS_HOST = "{{ range service "global-redis-check" }}{{ .Address }}{{ end }}"
          SPRING_DATA_REDIS_PORT = "{{ range service "global-redis-check" }}{{ .Port }}{{ end }}"
        EOH

        destination = "redis-server.env"
        env = true
      }

      driver = "docker"

      config {
        image = "kmandalas/urlshortener:latest"
        ports = ["http"]
      }
    }
  }
}
