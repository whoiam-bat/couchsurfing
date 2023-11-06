import {ServiceType} from "./service-type";

export class Review {
    travelersAmount!: number;
    nightsToStay!: number;
    message!: string;
    serviceType!: ServiceType;
    isAccepted!: boolean;
    timestamp!: Date;
}
