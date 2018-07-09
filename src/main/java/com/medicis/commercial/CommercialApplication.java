package com.medicis.commercial;

import com.medicis.commercial.domain.*;
import com.medicis.commercial.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class CommercialApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommercialApplication.class, args);
    }

    @Component
    public static class MedicisCommercialApplication implements ApplicationRunner {
        private UserRepository userRepository;
        private HospitalRepository hospitalRepository;
        private HospitalEmployeeRepository hospitalEmployeeRepository;
        private HospitalsImagesRepository hospitalsImagesRepository;
        @Autowired
        DiagnosisRepository diagnosisRepository;

        public MedicisCommercialApplication(UserRepository userRepository, HospitalRepository hospitalRepository,
                                            HospitalEmployeeRepository hospitalEmployeeRepository, HospitalsImagesRepository hospitalsImagesRepository) {
            this.userRepository = userRepository;
            this.hospitalRepository = hospitalRepository;
            this.hospitalEmployeeRepository = hospitalEmployeeRepository;
            this.hospitalsImagesRepository = hospitalsImagesRepository;

        }

        /*TODO dodaj appointment za usere*/
        /*TODO dodaj nove bolnice*/
        @Override
        public void run(ApplicationArguments args) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userRepository.saveAndFlush(new User("Haris", "Dzafic", "M", "dzafe1987@gmail.com", bCryptPasswordEncoder.encode("HarisHaris"), "images/avatar5.png", true, "USER"));
            userRepository.save(new User("Haris", "Dzafic","admin@gmail.com",bCryptPasswordEncoder.encode("HarisHaris"),"images/avatar5.png",true,"ADMIN"));
            Hospital hospital = new Hospital("nejra@n.com", bCryptPasswordEncoder.encode("HarisHaris"), "Kosevo", "Bosnia and Herzegovina", "Sarajevo", "71000", "Kosevska 21", "Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet..", 141, 581, true);
            hospitalRepository.save(hospital);

            hospitalEmployeeRepository.save(new HospitalEmployee("Kerim", "Balic", "kera@k.com", "Lorem ispum dolor sit amet. Lorem ispum dolor sit amet. Lorem ispum dolor sit amet.", "/images/hospitals employees/loading.png", "Doctor", new Date(), true, hospital));
            hospitalEmployeeRepository.save(new HospitalEmployee("Adnan", "Mehonic", "ado@a.com", "Lorem ispum dolor sit amet. Lorem ispum dolor sit amet. Lorem ispum dolor sit amet.", "/images/hospitals employees/loading.png", "Doctor", new Date(), true, hospital));
            hospitalEmployeeRepository.save(new HospitalEmployee("Nejra", "Grabovica", "nejra@n.com", "Lorem ispum dolor sit amet. Lorem ispum dolor sit amet. Lorem ispum dolor sit amet.", "/images/hospitals employees/loading.png", "Doctor", new Date(), true, hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals employees/loading.png", hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals3.jpg", hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals4.jpg", hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals5.jpg", hospital));
            hospitalsImagesRepository.save(new HospitalsImages("/images/hospitals6.jpg", hospital));

            Random rand = new Random();

            diagnosisRepository.save(new Diagnosis("AIDS (HIV) Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Conservative treatment", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal aortic aneurysm", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal aortic aneurysm Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal aorta endovascular prosthetics (stenting)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal aorta open prosthetics", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Endovascular prosthesis bifurcation of the abdominal aorta", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Replacement of prosthetic abdominal aorta", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal obesity", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominoplasty", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Subcutaneous liposuction", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Obesity (overweight) Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Laser liposuction", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal rectus muscle diastasis", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abdominal rectus muscle diastasis Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abnormal breast areola", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abnormal breast areola Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Bartholin gland abscess", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abscess Bartholin gland Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abscess douglas space", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Abscess Douglas space Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Opening and drainage of abscess", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Achilles tendon bursitis or tendinitis", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Achilles tendon bursitis or tendinitis Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Ankle minimally invasive surgery", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Achilles tendon rupture", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Achilles tendon rupture Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acne", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acne Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Radical top skin layer removal with plastic reconstruction", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acoustic meatus defect", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acoustic neuroma", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acoustic neuroma (vestibular schwannoma) ", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Microsurgical resection", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Radiosurgery (Gamma Knife)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acromegaly", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acromegaly Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Surgical treatment (resection of pituitary adenomas)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acute bronchitis", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acute glaucoma", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Diagnosis and symptomatic treatment", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acute lymphoblastic leukemia (all)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("High-intensity induction chemotherapy 1 block)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Stem cell transplant", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acute myeloid leukemia (AML) Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Radiation therapy of brain", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Consolidation treatment", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("High-intensity induction chemotherapy 1 cycle", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Acute pancreatitis with pancreonecrosis", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Necrosectomy without artificial lung ventilation", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Necrosectomy, artificial lung ventilation in intensive care unit", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Adrenal cancer", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Adrenal cancer Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Radiotherapy", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Surgery", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Advanced ring vaginal", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Advanced Ring vaginal Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Vaginal repair (vaginoplasty)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Aggressive t-cell lymphoma", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Chemotherapy", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Alopecia", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Transplantation of hair follicles with robotic system-FUE ARTAS", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Hormonal disorder in women with androgenic alopecia (hair loss) Diagnostic", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Alzheimers disease", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Diagnosis and conservative treatment", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Amyotrophic lateral sclerosis (ALS)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Anal stenosis", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Sphincterotomy", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Angina pectoris (heart attack)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Coronary bypass surgery (cabg)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Сardiac catheterization and coronary bypass surgery (cabg)", rand.nextInt((30 - 1) + 1) + 1));
            diagnosisRepository.save(new Diagnosis("Сardiac catheterization with stent placement", rand.nextInt((30 - 1) + 1) + 1));
        }
    }
}
