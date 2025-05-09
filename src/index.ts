import Fastify from 'fastify'
import 'dotenv/config'

const fastify = Fastify({
  logger: true,
})

const start = async () => {
  try {
    await fastify.listen({ port: Number(process.env.SERVER_PORT) })
  } catch (err) {
    fastify.log.error(err)
    process.exit(1)
  }
}

start()
