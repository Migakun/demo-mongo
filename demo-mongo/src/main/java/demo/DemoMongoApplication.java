package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class DemoMongoApplication implements CommandLineRunner {
	
	@Autowired MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoMongoApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		Foo foo = new Foo();
		foo.setId("foo1");
		Bar bar = new Bar();
		bar.setPrevious(new Bar());
		foo.setBar(bar);
		mongoTemplate.save(foo);
		
		Worker worker = new Worker();
		worker.setId("a");
		
		Job workerJob = new Job();
		Salary workerSalary = new Salary();
		workerSalary.setAmount("1");
		workerJob.setSalary(workerSalary);
		worker.setJob(workerJob);
		
		Partner partner = new Partner();
		Job partnerJob = new Job();
		Salary partnerSalary = new Salary();
		partnerSalary.setAmount("2");
		partnerJob.setSalary(partnerSalary);
		partner.setJob(partnerJob);
		
		worker.setPartner(partner);
		
		mongoTemplate.save(worker);
	}
}
