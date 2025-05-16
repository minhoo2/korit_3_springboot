package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.packt.cardatabase.domain.CarRepository;

import java.util.Arrays;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository repository;
	private final OwnerRepository oRepository;
	private final AppUserRepository uRepository;

	public CardatabaseApplication(CarRepository repository, OwnerRepository oRepository, AppUserRepository uRepository) {
		this.repository = repository;
		this.oRepository = oRepository;
        this.uRepository = uRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("애플리케이션 실행");
	}

	@Override
	public void run(String... args) throws Exception {

		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		Owner owner3 = new Owner("근수", "안");
		oRepository.saveAll(Arrays.asList(owner1, owner2, owner3));

		repository.save(new Car("Ford", "Mustang", "Red", "ADF-11121", 2023,59000, owner1));
		repository.save(new Car("Nissan", "Leaf","White", "SSJ-3002", 2020,29000, owner1));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2022,39000, owner2));
		repository.save(new Car("Kia", "Seltos", "Chacoal", "360수5690", 2020,28000,owner3));

		for (Car car : repository.findAll()) {
			logger.info("브랜드: {}, 모델명: {}", car.getBrand(), car.getModel());
		}

		// 사용자명 : user, 비밀번호 : user
		uRepository.save(new AppUser("user", "$2y$04$bu.eVbuvAA8wy7dJJEAr8.k35R14DEQW2iwbo0ICLtVuLodMLM1O2", "USER"));
		// 사용자명 : admin, 비밀번호 : admin
		uRepository.save(new AppUser("admin", "$2y$04$ZGccH1C94UsOUsvQrs1N3.H91vYIseYfANbWTaziqnzGtSWcwHbC2", "ADMIN"));
	}
}
