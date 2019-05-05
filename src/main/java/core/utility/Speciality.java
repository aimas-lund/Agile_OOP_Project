package core.utility;

public enum Speciality {
    ALLERGYANDIMMUNOLOGY("Allergy & immunology"),
    ANESTHESIOLOGY("Anesthesiology"),
    DERMATOLOGY("Dermatology"),
    DIAGNOSTICRADIOLOGY("Diagnostic radiology"),
    EMERGENCYMEDICINE("Emergency Medicine"),
    FAMILYMEDICINE("Family Medicine"),
    INTERNALMEDICINE("Internal Medicine"),
    MEDICALGENETICS("Medical Genetics"),
    NEUROLOGY("Neurology"),
    NUCLEARMEDICINE("Nuclear Medicine"),
    OBSTETRICSANDGYNECOLOGY("Obstetrics and gynecology"),
    OPHTHALMOLOGY("Ophthalmology"),
    PATHOLOGY("Pathology"),
    PEDIATRICS("Pediatrics"),
    PHYSICALMEDICINEANDREHABILITATION("Physical medicine & rehabilitation"),
    PREVENTIVEMEDICINE("Preventive medicine"),
    RADIATIONONCOLOGY("Radiation oncology"),
    SURGERY("Surgery"),
    UROLOGY("Urology");

    private final String specialty;

    Speciality(String specialty) {
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

}
