import {UserInfo} from "./user-info";
import {UserHome} from "./user-home";
import {IncomingRequest} from "./incoming-request";
import {Review} from "./review";
import {Trip} from "./trip";

export class User {
  _id!: string;
  login!: string;
  email!: string;
  password!: string;
  dateCreated!: Date;
  isVerified!: boolean;
  userInfo!: UserInfo;
  userHome!: UserHome;
  requests!: IncomingRequest[];
  reviews!: Review[];
  trips!: Trip[];
}
