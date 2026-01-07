## AI Tonality Analysis Service
Spring Boot приложение для анализа тональности текста, развернутое в Minikube.

## Быстрый старт
1. Сборка и локальный запуск:
  - mvn clean package
  - java -jar target/ai-tonality-1.0.0.jar
2. Запуск в Minikube
  - minikube start --cpus=4 --memory=8192mb
  - eval $(minikube docker-env)
  - docker build -t ai-tonality:latest .
3. Развертывание
  - kubectl apply -f k8s/
  - minikube service tonality-service --url

## Использование приложения
### Анализ тональности
#### GET запрос
curl "http://<minikube-ip>/api/tonality?text=This%20is%20awesome"

#### POST запрос
curl -X POST http://<minikube-ip>/api/tonality \
-H "Content-Type: application/json" \
-d '{"text": "I feel great today"}'

### Проверка здоровья
curl http://<minikube-ip>/health

## Конфигурация
### Kubernetes манифесты
  - deployment.yaml - 3 реплики приложения
  - service.yaml - LoadBalancer сервис
  - hpa.yaml - автомасштабирование (CPU > 70%)
  - ingress.yaml - маршрутизация трафика

### Мониторинг
Prometheus/Grafana:
- helm install prometheus prometheus-community/kube-prometheus-stack

Доступ к Grafana:
- kubectl port-forward service/prometheus-grafana 3000:80

## Метрики
### Приложение предоставляет метрики по адресу:
  - /actuator/health - статус здоровья
  - /actuator/prometheus - метрики для Prometheus

## Требования
- Java 17
- Maven 3.9+
- Docker
- Minikube
- kubectl