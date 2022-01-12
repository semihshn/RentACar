echo "Starting postgres container.."
docker run --name btk-postgres \
  -e POSTGRES_PASSWORD=12345 \
  -d \
  -p 5432:5432 \
  postgres