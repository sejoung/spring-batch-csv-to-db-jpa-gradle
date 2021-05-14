package io.github.sejoung.configration;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BatchConfiguration implements BatchConfigurer {

  private final EntityManagerFactory entityManagerFactory;

  private final PlatformTransactionManager transactionManager;

  private final JobRepository jobRepository;

  private final JobLauncher jobLauncher;

  private final JobExplorer jobExplorer;

  public BatchConfiguration(EntityManagerFactory entityManagerFactory) {

    this.entityManagerFactory = entityManagerFactory;
    try {
      this.transactionManager = buildTransactionManager();
      MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean(
        getTransactionManager());
      jobRepositoryFactory.afterPropertiesSet();
      this.jobRepository = jobRepositoryFactory.getObject();
      MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(
        jobRepositoryFactory);
      jobExplorerFactory.afterPropertiesSet();
      this.jobExplorer = jobExplorerFactory.getObject();
      this.jobLauncher = createJobLauncher();
    } catch (Exception ex) {
      throw new IllegalStateException("Unable to initialize Spring Batch", ex);
    }
  }

  @Override
  public JobRepository getJobRepository() {
    return this.jobRepository;
  }

  @Override
  public PlatformTransactionManager getTransactionManager() {
    return this.transactionManager;
  }

  @Override
  public JobLauncher getJobLauncher() {
    return this.jobLauncher;
  }

  @Override
  public JobExplorer getJobExplorer() {
    return this.jobExplorer;
  }

  private JobLauncher createJobLauncher() throws Exception {
    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
    jobLauncher.setJobRepository(getJobRepository());
    jobLauncher.afterPropertiesSet();
    return jobLauncher;
  }

  private PlatformTransactionManager createTransactionManager() {
    return new JpaTransactionManager(this.entityManagerFactory);
  }

  private PlatformTransactionManager buildTransactionManager() {
    return createTransactionManager();
  }

}
