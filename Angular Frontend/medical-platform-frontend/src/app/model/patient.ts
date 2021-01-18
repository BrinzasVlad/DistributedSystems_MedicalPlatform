export class Patient {
    id: number;
    name: string;
    birthDate: string; // FIXME: switch to actually using TypeScipt dates (fix the JSON conversion)
    // birthDate: Date;
    gender: string;
    address: string;
}
