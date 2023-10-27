import {ServiceType} from "./service-type";

export class IncomingRequest {
    travelersAmount!: number;
    nightsToStay!: number;
    message!: string;
    serviceType!: ServiceType;
    isAccepted!: boolean;
    timestamp!: Date;
}
