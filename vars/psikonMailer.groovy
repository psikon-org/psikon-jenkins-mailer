def signatures = """
  _______   _______   ___   ___ ___    _______   ______
 |   _   | |   _   | |   | |   Y   )  |   _   | |   _  \\
 |.  1   | |   1___| |.  | |.  1  /   |.  |   | |.  |   |
 |.  ____| |____   | |.  | |.  _  \\   |.  |   | |.  |   |
 |:  |     |:  1   | |:  | |:  |   \\  |:  1   | |:  |   |
 |::.|     |::.. . | |::.| |::.| .  ) |::.. . | |::.|   |
 `---'     `-------' `---' `--- ---'  `-------' `--- ---'

                  https://psikon.org
"""

def call(currentBuild, env) {
  script {
    def subject = ""
    def body    = ""
    subject = "[psikon-ci] ${env.JOB_NAME} (${env.BUILD_NUMBER}) - ${currentBuild.result}!"

    body = """${env.JOB_NAME} (${env.BUILD_NUMBER}) has finished with status ${currentBuild.result}.

Duration: ${currentBuild.durationString}
URL     : ${currentBuild.absoluteUrl}

${signatures}"""

    env.PSIKON_EMAIL_BODY = body;
    env.PSIKON_EMAIL_SUBJECT = subject;

  }

  emailext(
    subject: env.PSIKON_EMAIL_SUBJECT,
    body: env.PSIKON_EMAIL_BODY,
    to: "ci@psikon.com"
  )
}