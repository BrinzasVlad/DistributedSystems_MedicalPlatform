export class MedicationTaken {
    id: number;
    patientId: number;
    medicationName: string;
    intervalStart: string;
    intervalEnd: string;
    date: string; // FIXME: use TypeScript Date instead!
    taken: boolean;
}