job "demo-webapp" {
  datacenters = ["europe-west1-c", "europe-west1-d"]

  group "demo" {
    count = 1

    scaling {
      enabled = true
      min     = 1
      max     = 4

      policy {
        evaluation_interval = "2s"
        cooldown            = "5s"

        check "cpu_usage" {
          source = "prometheus"
          query  = "avg(nomad_client_allocs_cpu_total_percent{task='server'})"
          strategy "target-value" {
            target = 25
          }
        }
      }
    }

    network {
      port "http" {
        to = 9090
      }
    }

    service {
      name = "demo-webapp"
      port = "http"
      check {
        type     = "http"
        path     = "/actuator/health"
        interval = "2s"
        timeout  = "1s"
      }
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
