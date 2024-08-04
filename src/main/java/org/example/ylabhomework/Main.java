package org.example.ylabhomework;

import services.*;
import models.*;
import repositories.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static UserService userService;
    private static CarService carService;
    private static OrderService orderService;
    private static ServiceRequestService serviceRequestService;
    private static AuditService auditService;

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        CarRepository carRepository = new CarRepository();
        OrderRepository orderRepository = new OrderRepository();
        ServiceRequestRepository serviceRequestRepository = new ServiceRequestRepository();
        AuditLogRepository auditLogRepository = new AuditLogRepository();

        userService = new UserService(userRepository);
        carService = new CarService(carRepository);
        orderService = new OrderService(orderRepository);
        serviceRequestService = new ServiceRequestService(serviceRequestRepository);
        auditService = new AuditService(auditLogRepository);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMainMenu();
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    handleUserRegistration(scanner);
                    break;
                case "2":
                    handleUserLogin(scanner);
                    break;
                case "3":
                    handleCarManagement(scanner, carRepository);
                    break;
                case "4":
                    handleOrderManagement(scanner, orderRepository);
                    break;
                case "5":
                    handleServiceRequestManagement(scanner, serviceRequestRepository);
                    break;
                case "6":
                    handleAuditLogManagement(scanner);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("=== Меню автосалона ===");
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Авторизация пользователя");
        System.out.println("3. Управление автомобилями");
        System.out.println("4. Управление заказами");
        System.out.println("5. Управление заявками на обслуживание");
        System.out.println("6. Управление журналом действий");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private static void handleUserRegistration(Scanner scanner) {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите роль (admin/manager/client): ");
        String role = scanner.nextLine();

        User user = new User(username, password, role);
        userService.registerUser(user);
        System.out.println("Пользователь успешно зарегистрирован.");
    }

    private static void handleUserLogin(Scanner scanner) {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if (userService.authenticateUser(username, password)) {
            System.out.println("Успешная авторизация.");
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
        }
    }

    private static void handleCarManagement(Scanner scanner, CarRepository carRepository) {
        System.out.println("=== Управление автомобилями ===");
        System.out.println("1. Добавить автомобиль");
        System.out.println("2. Редактировать автомобиль");
        System.out.println("3. Удалить автомобиль");
        System.out.println("4. Просмотреть все автомобили");
        System.out.print("Выберите действие: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Марка: ");
                String brand = scanner.nextLine();
                System.out.print("Модель: ");
                String model = scanner.nextLine();
                System.out.print("Год выпуска: ");
                int year = Integer.parseInt(scanner.nextLine());
                System.out.print("Цена: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Состояние: ");
                String condition = scanner.nextLine();
                Car car = new Car(carRepository.getSize(), brand, model, year, price, condition);
                carService.addCar(car);
                auditService.logAction("Добавление автомобиля", "admin");
                System.out.println("Автомобиль добавлен.");
                break;
            case "2":
                System.out.print("ID автомобиля: ");
                int carIdToUpdate = Integer.parseInt(scanner.nextLine());
                Optional<Car> carToUpdate = carService.getCarById(carIdToUpdate);
                if (carToUpdate.isPresent()) {
                    Car car2 = carToUpdate.get();
                    System.out.print("Новая марка: ");
                    car2.setBrand(scanner.nextLine());
                    System.out.print("Новая модель: ");
                    car2.setModel(scanner.nextLine());
                    System.out.print("Новый год выпуска: ");
                    car2.setYear(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Новая цена: ");
                    car2.setPrice(Double.parseDouble(scanner.nextLine()));
                    System.out.print("Новое состояние: ");
                    car2.setCondition(scanner.nextLine());
                    carService.updateCar(car2);
                    auditService.logAction("Редактирование автомобиля", "admin");
                    System.out.println("Автомобиль обновлен.");
                } else {
                    System.out.println("Автомобиль не найден.");
                }
                break;
            case "3":
                System.out.print("ID автомобиля: ");
                int carIdToDelete = Integer.parseInt(scanner.nextLine());
                if (carService.deleteCar(carIdToDelete)) {
                    auditService.logAction("Удаление автомобиля", "admin");
                    System.out.println("Автомобиль удален.");
                } else {
                    System.out.println("Автомобиль не найден.");
                }
                break;
            case "4":
                List<Car> cars = carService.getAllCars();
                for (Car car4 : cars) {
                    System.out.println(car4);
                }
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова.");
        }
    }


    private static void handleOrderManagement(Scanner scanner, OrderRepository orderRepository) {
        System.out.println("=== Управление заказами ===");
        System.out.println("1. Создать заказ");
        System.out.println("2. Редактировать заказ");
        System.out.println("3. Удалить заказ");
        System.out.println("4. Просмотреть все заказы");
        System.out.print("Выберите действие: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Марка автомобиля: ");
                String carBrand = scanner.nextLine();
                System.out.print("Модель автомобиля: ");
                String carModel = scanner.nextLine();
                System.out.print("Имя клиента: ");
                String customerUsername = scanner.nextLine();
                System.out.print("Статус заказа: ");
                String status = scanner.nextLine();
                Order order = new Order(orderRepository.getSize(), carBrand, carModel, customerUsername, status);
                orderService.addOrder(order);
                auditService.logAction("Создание заказа", "admin");
                System.out.println("Заказ создан.");
                break;
            case "2":
                System.out.print("ID заказа: ");
                int orderIdToUpdate = Integer.parseInt(scanner.nextLine());
                Optional<Order> orderToUpdate = orderService.getOrderById(orderIdToUpdate);
                if (orderToUpdate.isPresent()) {
                    Order order2 = orderToUpdate.get();
                    System.out.print("Новая марка автомобиля: ");
                    order2.setCarBrand(scanner.nextLine());
                    System.out.print("Новая модель автомобиля: ");
                    order2.setCarModel(scanner.nextLine());
                    System.out.print("Новое имя клиента: ");
                    order2.setCustomerUsername(scanner.nextLine());
                    System.out.print("Новый статус заказа: ");
                    order2.setStatus(scanner.nextLine());
                    orderService.updateOrder(order2);
                    auditService.logAction("Редактирование заказа", "admin");
                    System.out.println("Заказ обновлен.");
                } else {
                    System.out.println("Заказ не найден.");
                }
                break;
            case "3":
                System.out.print("ID заказа: ");
                int orderIdToDelete = Integer.parseInt(scanner.nextLine());
                if (orderService.deleteOrder(orderIdToDelete)) {
                    auditService.logAction("Удаление заказа", "admin");
                    System.out.println("Заказ удален.");
                } else {
                    System.out.println("Заказ не найден.");
                }
                break;
            case "4":
                List<Order> orders = orderService.getAllOrders();
                for (Order order4 : orders) {
                    System.out.println(order4);
                }
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова.");
        }
    }


    private static void handleServiceRequestManagement(Scanner scanner, ServiceRequestRepository serviceRequestRepository) {
        System.out.println("=== Управление заявками на обслуживание ===");
        System.out.println("1. Создать заявку");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Просмотреть все заявки");
        System.out.print("Выберите действие: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Марка автомобиля: ");
                String carBrand = scanner.nextLine();
                System.out.print("Модель автомобиля: ");
                String carModel = scanner.nextLine();
                System.out.print("Имя клиента: ");
                String customerUsername = scanner.nextLine();
                System.out.print("Тип обслуживания: ");
                String serviceType = scanner.nextLine();
                System.out.print("Статус заявки: ");
                String status = scanner.nextLine();
                ServiceRequest serviceRequest = new ServiceRequest(serviceRequestRepository.getSize(), carBrand, carModel, customerUsername, serviceType, status);
                serviceRequestService.addServiceRequest(serviceRequest);
                auditService.logAction("Создание заявки на обслуживание", "admin");
                System.out.println("Заявка создана.");
                break;
            case "2":
                System.out.print("ID заявки: ");
                int requestIdToUpdate = Integer.parseInt(scanner.nextLine());
                Optional<ServiceRequest> requestToUpdate = serviceRequestService.getServiceRequestById(requestIdToUpdate);
                if (requestToUpdate.isPresent()) {
                    ServiceRequest serviceRequest2 = requestToUpdate.get();
                    System.out.print("Новая марка автомобиля: ");
                    serviceRequest2.setCarBrand(scanner.nextLine());
                    System.out.print("Новая модель автомобиля: ");
                    serviceRequest2.setCarModel(scanner.nextLine());
                    System.out.print("Новое имя клиента: ");
                    serviceRequest2.setCustomerUsername(scanner.nextLine());
                    System.out.print("Новый тип обслуживания: ");
                    serviceRequest2.setServiceType(scanner.nextLine());
                    System.out.print("Новый статус заявки: ");
                    serviceRequest2.setStatus(scanner.nextLine());
                    serviceRequestService.updateServiceRequest(serviceRequest2);
                    auditService.logAction("Редактирование заявки на обслуживание", "admin");
                    System.out.println("Заявка обновлена.");
                } else {
                    System.out.println("Заявка не найдена.");
                }
                break;
            case "3":
                System.out.print("ID заявки: ");
                int requestIdToDelete = Integer.parseInt(scanner.nextLine());
                if (serviceRequestService.deleteServiceRequest(requestIdToDelete)) {
                    auditService.logAction("Удаление заявки на обслуживание", "admin");
                    System.out.println("Заявка удалена.");
                } else {
                    System.out.println("Заявка не найдена.");
                }
                break;
            case "4":
                List<ServiceRequest> requests = serviceRequestService.getAllServiceRequests();
                for (ServiceRequest request : requests) {
                    System.out.println(request);
                }
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова.");
        }
    }


    private static void handleAuditLogManagement(Scanner scanner) {
        System.out.println("=== Управление журналом действий ===");
        System.out.println("1. Просмотреть все действия");
        System.out.println("2. Экспортировать журнал действий");
        System.out.print("Выберите действие: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                List<AuditLog> logs = auditService.getAllLogs();
                for (AuditLog log : logs) {
                    System.out.println(log);
                }
                break;
            case "2":
                System.out.print("Введите имя файла для экспорта: ");
                String filename = scanner.nextLine();
                auditService.exportLogs(filename);
                System.out.println("Журнал действий экспортирован в файл " + filename);
                break;
            default:
                System.out.println("Неверный выбор, попробуйте снова.");
        }
    }

}
