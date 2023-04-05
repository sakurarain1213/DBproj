UPDATE `sql`.paper SET paperName = '论文1', paperAuthor = '张三', paperDate = '2022-12-24', paper = null, paperInfo = '一个测试', paperCondition = '审核中', type = 0 WHERE id = 1;
UPDATE `sql`.paper SET paperName = '论文2', paperAuthor = '李四', paperDate = null, paper = null, paperInfo = null, paperCondition = '已通过', type = 0 WHERE id = 2;
UPDATE `sql`.paper SET paperName = '论文3', paperAuthor = '王五', paperDate = null, paper = null, paperInfo = null, paperCondition = '已驳回', type = 0 WHERE id = 3;
UPDATE `sql`.paper SET paperName = '项目1', paperAuthor = '', paperDate = '2022-12-25', paper = null, paperInfo = '测试', paperCondition = '审核中', type = 1 WHERE id = 4;
