import {Gender} from "./gender";

export class UserHome {
    availableHostNights!: number;
    maxGuests!: number;
    preferredGender!: Gender;
    kidFriendly!: boolean;
    petFriendly!: boolean;
    wheelchairAllowed!: boolean;
    smokingAllowed!: boolean;
    iHaveKids!: boolean;
    iHavePets!: boolean;
    iAmSmoker!: boolean;
    otherInfo!: boolean;
}
