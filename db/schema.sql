CREATE TABLE "accident" (
	"id" serial NOT NULL,
	"name" varchar(2000),
	"text_" varchar(2000),
	"address" varchar(2000),
	"accident_type_id" int NOT NULL,
	CONSTRAINT "accident_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "accident_type" (
	"id" serial NOT NULL,
	"name" varchar(2000) NOT NULL,
	CONSTRAINT "accident_type_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "rule" (
	"id" serial NOT NULL,
	"name" varchar(2000) NOT NULL,
	CONSTRAINT "rule_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "accident_rule" (
	"accident_id" serial NOT NULL,
	"rules_id" serial NOT NULL
) WITH (
  OIDS=FALSE
);



ALTER TABLE "accident" ADD CONSTRAINT "accident_fk0" FOREIGN KEY ("accident_type_id") REFERENCES "accident_type"("id");



ALTER TABLE "accident_rule" ADD CONSTRAINT "accident_rule_fk0" FOREIGN KEY ("accident_id") REFERENCES "accident"("id");
ALTER TABLE "accident_rule" ADD CONSTRAINT "accident_rule_fk1" FOREIGN KEY ("rules_id") REFERENCES "rule"("id");

INSERT INTO "accident_type" VALUES (0, 'Две машины');
INSERT INTO "accident_type" VALUES (1, 'Машина и человек');
INSERT INTO "accident_type" VALUES (2, 'Машина и велосипед');
commit;

INSERT INTO "rule" VALUES (0, 'Статья. 1');
INSERT INTO "rule" VALUES (1, 'Статья. 2');
INSERT INTO "rule" VALUES (2, 'Статья. 3');
commit;
