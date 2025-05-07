export interface Customer {
  id: string
  firstName: string
  lastName: string
  email: string
  password: string
  birthDate: Date
  phoneNumber: string
  gender: Gender
  createdAt: Date
  updatedAt: Date
}

enum Gender {
  MALE = "MALE",
  FEMALE = "FEMALE",
  OTHER = "OTHER",
}