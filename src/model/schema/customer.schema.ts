import { z } from 'zod'

export const createUserSchema = z
  .object({
    firstName: z.string(),
    lastName: z.string(),
    email: z.string().email(),
    password: z.string().min(8),
    birthDate: z.string().transform((val) => new Date(val)),
    phoneNumber: z.string().min(11).max(14),
    gender: z.enum(['MALE', 'FEMALE', 'OTHER']),
  })
  .strict()
