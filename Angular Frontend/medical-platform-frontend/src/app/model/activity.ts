export class Activity {
    id: number;
    name: string;
    start: string;
    end: string; // FIXME: use TypeScript Date instead!
    abnormal: boolean;
    justified: boolean;
    recommendation: string;
    patientId: number;
}
